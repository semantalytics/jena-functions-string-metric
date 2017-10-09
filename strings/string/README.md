[![Build Status](https://travis-ci.org/semantalytics/stardog-udf-strings.svg?branch=master)](https://travis-ci.org/semantalytics/stardog-udf-strings)

# stardog-udf-strings

Stardog user defined functions for manipulating strings

prefix string: <http://semantalytics.com/2016/03/ns/stardog/function/strings/>

Installation: Place stardog-udf-strings-1.0.0-sd4.2.jar into $STARDOG_HOME/server/ext/ or $STARDOG_EXT/ and restart Stardog

## CaseFormat

usage: string:caseFormat(fromFormat, toFormat, stringToBeFormatted)

fromFormat and toFormat can be any one of the following

caseFormat
CaseFormat
case-format
case_format
CASE_FORMAT

## Join

useage: string:join(on, str...)

on: string separator
str... : one or more strings to join

This function can be accieved by the use of the built in CONCAT function but with repeated enumeration of the join character 
