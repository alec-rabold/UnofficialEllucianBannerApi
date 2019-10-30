# Ellucian Banner REST API (Unofficial)

<!-- TOC depthFrom:1 depthTo:2 withLinks:1 updateOnSave:1 orderedList:0 -->

- [Ellucian Banner REST API (Unofficial)](#crossref-rest-api)
    - [Preamble](#preamble)
    - [API overview](#api-overview)
    - [Resource components](#resource-components)
    - [Parameters](#parameters)
    - [Sample Flow](#sample-flow)
    

<!-- /TOC -->



## Preamble

The Ellucian Banner REST API (Unofficial) allows anybody to request up-to-date registration data from more than 750+ universities that use Ellucian's Banner software.


## API overview

The API is generally RESTFUL and returns results in JSON. JSON formats returned by the API are documented [here](#responses).

The API supports HTTP and HTTPS. Examples here are provided using HTTPS.

The following route is the base path:

`https://api.collegeplanner.io/{version}/`

## Resource components
Major resource components supported by the API are:

- colleges
- terms
- subjects
- courses
- section

The following can be used alone:

| resource      | description                       |
|:--------------|:----------------------------------|
| `/colleges`      | returns a list of colleges currently supported by the API

## Parameters

Parameters are used to control the results returned by the API. At this time, they must be passed as URI parameters in the request.

| parameter                    | description                 |
|:-----------------------------|:----------------------------|
| `college`                      | shortened name of the university you want registration data for (e.g. "GeorgiaTech") |
| `term`| code which identifies the academic year and season (e.g. "201908" for Fall 2019) |
| `subject`| abbreviated name of the department (e.g. "CS" in "CS-107") |
| `number`| course number for specific class (e.g. "107" in "CS-107") |

    
The following resources must be used with parameters:

| resource      | required parameters      | description                       |
|:--------------|:-------------------------|:----------------------------------|
| `/terms`      | `college`                |returns a list of all academic terms and respective term codes
| `/subjects`   | `college`, `term`        |returns a list of all departments / subjects offered for a specific term
| `/courses`    | `college`, `term`, `subject`   |returns a list of all courses within the specified department/subject
| `/section`    | `college`, `term`, `subject`, `number`   |returns a list of all sections and meeting times for a specific course 


### Example using URI parameters

    https://api.collegeplanner.io/v1/terms?college=GeorgiaTech

## Sample API flow

`GET /v1/colleges`
```
[
  {
    "abbr_name": "GeorgiaTech",
    "full_name": "Georgia Tech"
  },
  {
    "abbr_name": "Otterbein",
    "full_name": "Otterbein University"
  }, ...
]
```

`GET /v1/terms?college=GeorgiaTech`
```
[
  {
    "term_season": "Spring",
    "term_year": "2020",
    "term_code": "202002"
  },
  {
    "term_season": "Fall",
    "term_year": "2019",
    "term_code": "201908"
  }, ...
]
```


`GET /v1/subjects?college=GeorgiaTech&term=201908`
```
[
  {
    "subj_abbr": "ACCT",
    "subj_name": "Accounting"
  },
  {
    "subj_abbr": "AE",
    "subj_name": "Aerospace Engineering"
  },
  {
    "subj_abbr": "AS",
    "subj_name": "Air Force Aerospace Studies"
  }, ...
]
```

`GET /v1/courses?college=GeorgiaTech&term=201908&subject=ACCT`
```
[
  {
    "course_name": "ACCT 2101",
    "course_title": "Accounting I",
    "department": "ACCT",
    "course_number": "2101",
    "course_id": "86809"
  },
  {
    "course_name": "ACCT 2102",
    "course_title": "Accounting II",
    "department": "ACCT",
    "course_number": "2102",
    "course_id": "80836"
  }
]
```

`GET /v1/sections?college=GeorgiaTech&term=201908&subject=ACCT&number=2101`
```
[
  {
    "course_id": "86809",
    "section": "A",
    "meeting_times": [
      {
        "type": "Class",
        "days": "MWF",
        "time": "9:05 am - 9:55 am",
        "location": "College of Business 102",
        "instructors": "Wenqian Hu (P)",
        "date_range": "Aug 19, 2019 - Dec 12, 2019",
        "schedule_type": "Lecture*"
      }
    ]
  },
  {
    "course_id": "82789",
    "section": "B",
    "meeting_times": [
      {
        "type": "Class",
        "days": "MWF",
        "time": "11:15 am - 12:05 pm",
        "location": "College of Business 224",
        "instructors": "Wenqian Hu (P)",
        "date_range": "Aug 19, 2019 - Dec 12, 2019",
        "schedule_type": "Lecture*"
      }
    ]
  },
  {
    "course_id": "80835",
    "section": "C",
    "meeting_times": [
      {
        "type": "Class",
        "days": "MWF",
        "time": "12:20 pm - 1:10 pm",
        "location": "College of Business 224",
        "instructors": "Anish S Menon (P)",
        "date_range": "Aug 19, 2019 - Dec 12, 2019",
        "schedule_type": "Lecture*"
      }
    ]
  }, ...
]
