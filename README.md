# Versions as a Service
----
This REST endpoint returns the version history of a given peice of content


## How to Use
----

Once installed, you can access this resource by (this assumes you are on localhost)

`http://localhost:8080/api/v1/versions/all/{ID|INODE}`



## Authentication
----
This API supports the same REST auth infrastructure as other 
rest apis in dotcms. There are 4 ways to authenticate.

* user/xxx/password/yyy in the URI
* basic http/https authentication (base64 encoded)
* DOTAUTH header similar to basic auth and base64 encoded, e.g. setHeader("DOTAUTH", base64.encode("admin@dotcms.com:admin"))
* Session based (form based login) for frontend or backend logged in user
# com.dotcms.rest.versions
