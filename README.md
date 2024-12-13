[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/WpmyY8VXzpWRMEk9MTHKSj/DMYCCyeQyD2fAwHXcQ3Ysi/tree/main.svg?style=svg&circle-token=CCIPRJ_B7JipfsFwKV2fyTFXaSBL_7ac271ffaa0f7ea8d1e7872ca9874338894b88f7)](https://dl.circleci.com/status-badge/redirect/circleci/WpmyY8VXzpWRMEk9MTHKSj/DMYCCyeQyD2fAwHXcQ3Ysi/tree/main)

# Early phase api test project

- [Description](#description)
  - [Tools](#tools)
  - [CI integration:](#ci-integration)
  - [Run tests](#run-tests)

# Description

This project focuses on REST api test of a sample user scenario flow. 
Main scope and constraints are as follows:
  - Validate email formats in comments section for specific user's post.
  - Project is in early phase of api design. 
    - Test will be running via existing/external mock-server.

## Tools

- Java 21
- Rest Assured
- Maven

## CI integration:

- Circle CI
- jacoco-maven-plugin (Coverage reports can be seen via CI artifacts at target/site/jacoco/index.html)

## Run tests

Test can be run via Maven cli as follows:

```bash

$ mvn clean test

# or specific test groups
$ mvn test -Dgroups="flow"
$ mvn test -Dgroups="email_format"

```
**Remarks**: 
- Testcase is kept under _unit tests_ for the time being.
- Differentiation between _unit_ and _integration tests_ can be made by means of taggings or they can be moved to _integration tests_ in next phases.

Sample output:
```bash
$ mvn test -Dgroups="email_format"
..
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.249 s -- in com.draft.apitest.EmailsInCommentsValidityTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.129 s
[INFO] Finished at: 2024-12-12T14:40:51Z
[INFO] ------------------------------------------------------------------------

```
Test results can also be accessed via **circleci** [![CircleCI](https://dl.circleci.com/status-badge/img/circleci/WpmyY8VXzpWRMEk9MTHKSj/DMYCCyeQyD2fAwHXcQ3Ysi/tree/main.svg?style=svg&circle-token=CCIPRJ_B7JipfsFwKV2fyTFXaSBL_7ac271ffaa0f7ea8d1e7872ca9874338894b88f7)](https://dl.circleci.com/status-badge/redirect/circleci/WpmyY8VXzpWRMEk9MTHKSj/DMYCCyeQyD2fAwHXcQ3Ysi/tree/main) dashboard and artifacts.