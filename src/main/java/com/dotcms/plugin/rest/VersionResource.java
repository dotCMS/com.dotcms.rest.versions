package com.dotcms.plugin.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.dotcms.mock.request.MockAttributeRequest;
import com.dotcms.mock.request.MockHttpRequest;
import com.dotcms.mock.request.MockSessionRequest;
import com.dotcms.mock.response.BaseResponse;
import com.dotcms.repackage.javax.ws.rs.GET;
import com.dotcms.repackage.javax.ws.rs.Path;
import com.dotcms.repackage.javax.ws.rs.PathParam;
import com.dotcms.repackage.javax.ws.rs.Produces;
import com.dotcms.repackage.javax.ws.rs.QueryParam;
import com.dotcms.repackage.javax.ws.rs.core.Context;
import com.dotcms.repackage.javax.ws.rs.core.MediaType;
import com.dotcms.repackage.javax.ws.rs.core.Response;
import com.dotcms.rest.InitDataObject;
import com.dotcms.rest.WebResource;
import com.dotcms.uuid.shorty.ShortType;
import com.dotcms.uuid.shorty.ShortyId;
import com.dotmarketing.beans.Identifier;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.DotStateException;
import com.dotmarketing.business.NoSuchUserException;
import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.exception.DotRuntimeException;
import com.dotmarketing.exception.DotSecurityException;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.util.UtilMethods;
import com.google.common.collect.ImmutableMap;
import com.liferay.portal.model.User;

@Path("/v1/versions")
public class VersionResource {

  private final WebResource webResource = new WebResource();

  @Produces({MediaType.APPLICATION_JSON, "application/javascript"})
  @GET
  @Path("/all/{id}")
  public Response loadJson(@Context final HttpServletRequest request, @Context final HttpServletResponse response,
      @PathParam("id") final String id, @QueryParam("depth") final int limit)
      throws DotDataException, DotStateException, DotSecurityException {
    System.err.println("VersionResource hit");


    int showing = (limit > 100) ? 100 : (limit < 0) ? 0 : limit;

    final InitDataObject auth = webResource.init(false, request, false);
    final User user = auth.getUser();


    ShortyId shorty = APILocator.getShortyAPI().getShorty(id).orElseThrow(() -> new DotRuntimeException("no shorty"));
    final Identifier ident = (shorty.type == ShortType.IDENTIFIER) ? APILocator.getIdentifierAPI().find(shorty.longId)
        : APILocator.getIdentifierAPI().findFromInode(shorty.longId);

    List<Map<String, Object>> versions = new ArrayList<Map<String, Object>>();
    for (Contentlet con : APILocator.getContentletAPI().findAllVersions(ident, APILocator.systemUser(), false)) {
      versions.add(conToMap(con));
    }

    final Response.ResponseBuilder responseBuilder = Response.ok(ImmutableMap.of("versions", versions));
    return responseBuilder.build();

  }


  @Produces({MediaType.APPLICATION_JSON, "application/javascript"})
  @GET
  @Path("/diff/{id1}/{id2}")
  public Response loadJson(@Context final HttpServletRequest request, @Context final HttpServletResponse response,
      @PathParam("id1") final String id1, @PathParam("id2") final String id2)
      throws DotDataException, DotStateException, DotSecurityException, IOException, ServletException {
    System.err.println("VersionResource hit");



    final InitDataObject auth = webResource.init(false, request, false);
    final User user = auth.getUser();


    ShortyId shorty1 = APILocator.getShortyAPI().getShorty(id1).orElseThrow(() -> new DotRuntimeException("no shorty"));
    ShortyId shorty2 = APILocator.getShortyAPI().getShorty(id2).orElseThrow(() -> new DotRuntimeException("no shorty"));
    if (shorty1.subType != ShortType.CONTENTLET || shorty2.subType != ShortType.CONTENTLET) {
      throw new DotRuntimeException("invalid shorties");
    }

    Map<String, String> map = new HashMap<>();
    Contentlet con1 = APILocator.getContentletAPI().find(shorty1.longId, APILocator.systemUser(), false);


    map.put(shorty1.longId, contentToHtml(con1, request));
    final Response.ResponseBuilder responseBuilder = Response.ok(map);
    return responseBuilder.build();
  }


  private String contentToHtml(Contentlet con, HttpServletRequest request) throws IOException, ServletException {



    MockAttributeRequest mockReq = new MockAttributeRequest(
        new MockSessionRequest(new MockHttpRequest("localhost", "/html/portlet/ext/contentlet/view_contentlet_popup_inc.jsp").request())
            .request());
    mockReq.setAttribute("contentletId", con.getInode());
    HttpServletRequest requestProxy = mockReq.request();
    File temp = File.createTempFile("mock-temp", ".tmp");
    HttpServletResponse responseProxy =
        new com.dotcms.mock.response.MockHttpCaptureResponse(new BaseResponse().response(), temp).response();

    request.getRequestDispatcher("/html/portlet/ext/contentlet/view_contentlet_popup_inc.jsp").forward(requestProxy, responseProxy);
    return IOUtils.toString(new FileInputStream(temp));

  }



  private Map<String, Object> conToMap(final Contentlet con) throws NoSuchUserException, DotDataException, DotSecurityException {

    User user = APILocator.getUserAPI().loadUserById(con.getModUser());



    Map<String, Object> conMap = new HashMap<String, Object>();
    conMap.put("title", con.getTitle());
    conMap.put("modDate", UtilMethods.dateToJDBC(con.getModDate()));
    conMap.put("language", con.getLanguageId());
    conMap.put("inode", con.getInode());
    conMap.put("modUser", con.getModUser());
    conMap.put("modUserName", user.getFullName());
    conMap.put("folder", con.getFolder());
    conMap.put("host", con.getHost());



    return conMap;
  }
}
