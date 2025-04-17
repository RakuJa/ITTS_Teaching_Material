if [ "$EUID" -ne 0 ]
  then echo "Please run as root"
  exit
fi


# First start the service
sudo systemctl start mariadb
sudo systemctl enable mariadb

# Then create the db
mysql -u root -e "create database springbootdb";