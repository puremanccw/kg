set curdir=%~dp0
@echo ����Ŀ¼��%curdir%
cd %curdir%
cd ../../..
call mvn org.mybatis.generator:mybatis-generator-maven-plugin:0.0.11-SNAPSHOT:generate
cd src/main/resources
pause