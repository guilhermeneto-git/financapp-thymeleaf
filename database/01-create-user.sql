-- Drop user first if it exists
DROP USER if exists 'personalfinanc'@'%' ;

-- Now create user with prop privileges
CREATE USER 'personalfinanc'@'%' IDENTIFIED BY 'personalfinanc';

GRANT ALL PRIVILEGES ON * . * TO 'personalfinanc'@'%';