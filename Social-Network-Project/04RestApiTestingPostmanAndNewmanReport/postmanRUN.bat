@echo off

cd "Postman REST API Testing&Newman Report"       <----- NAME OF THE FOLDER

newman run "WEare Social Network API.postman_collection.json" -e "SocialNetworkEnvironment.postman_environment.json" --reporters cli,htmlextra

echo Postman collection execution completed.
