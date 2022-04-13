Feature: Student service API
    Scenario: Student list
        When the list of students is requested
        Then the response contains the student with the id 'je857019'