#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "sysreserv" --dbname "usr" <<-EOSQL
    CREATE DATABASE establishment WITH OWNER sysreserv;
    CREATE DATABASE reservation WITH OWNER sysreserv
EOSQL
