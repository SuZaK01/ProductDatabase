#!/usr/bin/env bash

set -e

get_sql() {
cat <<EOF
drop database if exists productdb;
drop user if exists productuser;
CREATE ROLE productuser PASSWORD 'trudnehaslo123' LOGIN;
${1}
CREATE DATABASE productdb with owner='productuser';
grant all privileges on database productdb to productuser;
grant CONNECT on DATABASE productdb to productuser;
EOF
}

main() {
    if [[ $1 == "rds" ]]; then
        get_sql "GRANT dbaclsuser to defaultuser;"
    elif [[ $1 == "postgres" ]]; then
        get_sql
    else
        get_sql | psql -X -U postgres -h localhost --set ON_ERROR_STOP=on
    fi
}

main "$@"

