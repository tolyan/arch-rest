arch-rest
=========

Archetype for REST server based on CXF and MyBatis.

1. Configure DB
1.1. URL, USER, PASSWORD  in resources/mybatis.xml

2. Configure Principal population in pro.example.service.security.SecurityFilter.getUsers()
2.1. See example of SecurityFilter usage in pro.example.service.TestService

3. Configure archetypeId and groupId in pom.xml
