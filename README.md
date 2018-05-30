# Navigation as a Service
----
This REST endpoint returns the navigation as defined in dotCMS as a json object


## How to Use
----

Once installed, you can access this resource by (this assumes you are on localhost)

`http://localhost:8080/api/v1/nav/?depth=5`

or this, where you can specify a starting point for the nav

`http://localhost:8080/api/v1/nav/about-us/?depth=2`


## Returns
```json
{
code: null,
children: [
{
code: null,
children: [
{
code: null,
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/flights/index",
title: "Flight Search",
type: "htmlpage",
hash: 1165454360,
target: "_self",
order: 0
}
],
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/flights",
title: "Flights",
type: "folder",
hash: 1078679786,
target: "_self",
order: 1
},
{
code: null,
children: [
{
code: null,
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/holidays/index",
title: "View All Holidays",
type: "htmlpage",
hash: 533134847,
target: "_self",
order: 1
},
{
code: null,
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/holidays/great-britain",
title: "Great Britain",
type: "folder",
hash: 414222496,
target: "_self",
order: 2
},
{
code: null,
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/holidays/greece",
title: "Greece",
type: "folder",
hash: 1297463962,
target: "_self",
order: 3
}
],
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/holidays",
title: "Holidays",
type: "folder",
hash: 1252833965,
target: "_self",
order: 2
},
{
code: null,
children: [
{
code: null,
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/destinations/index",
title: "View All Desintations",
type: "htmlpage",
hash: 956597230,
target: "_self",
order: 0
}
],
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/destinations",
title: "Destinations",
type: "folder",
hash: 1464022994,
target: "_self",
order: 3
},
{
code: null,
children: [
{
code: null,
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/deals/index",
title: "View All Deals",
type: "htmlpage",
hash: 996016417,
target: "_self",
order: 0
}
],
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/deals",
title: "Deals",
type: "folder",
hash: 2014676549,
target: "_self",
order: 4
},
{
code: null,
children: [
{
code: null,
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/cruise/index",
title: "Search Cruises",
type: "htmlpage",
hash: 522766879,
target: "_self",
order: 1
}
],
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/cruise",
title: "Cruise",
type: "folder",
hash: 1685906543,
target: "_self",
order: 5
},
{
code: null,
children: [
{
code: null,
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/city-breaks/index",
title: "Browse All City Breaks",
type: "htmlpage",
hash: 2071527776,
target: "_self",
order: 1
},
{
code: null,
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/city-breaks/rome",
title: "Rome",
type: "htmlpage",
hash: 146159810,
target: "_self",
order: 2
}
],
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/city-breaks",
title: "City Breaks",
type: "folder",
hash: 69010192,
target: "_self",
order: 6
},
{
code: null,
children: [
{
code: null,
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/travel-advice/index",
title: "Travel Advice",
type: "htmlpage",
hash: 1260568438,
target: "_self",
order: 1
},
{
code: null,
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/travel-advice/travel-safety",
title: "Travel Safety",
type: "htmlpage",
hash: 2019804531,
target: "_self",
order: 2
}
],
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/travel-advice",
title: "Travel Advice",
type: "folder",
hash: 1244547558,
target: "_self",
order: 7
}
],
host: "e884ce2a-41fc-4a50-a4bf-575db001abd4",
href: "/System foldersystem folder",
title: "System folder",
type: "folder",
hash: 1769227458,
target: "_self",
order: 0
}
```


## Authentication
----
This API supports the same REST auth infrastructure as other 
rest apis in dotcms. There are 4 ways to authenticate.

* user/xxx/password/yyy in the URI
* basic http/https authentication (base64 encoded)
* DOTAUTH header similar to basic auth and base64 encoded, e.g. setHeader("DOTAUTH", base64.encode("admin@dotcms.com:admin"))
* Session based (form based login) for frontend or backend logged in user
# com.dotcms.rest.versions
