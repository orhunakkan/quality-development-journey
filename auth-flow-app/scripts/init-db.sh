#!/bin/bash

# Database initialization script
# Creates the practice_sessions database for the auth-flow-app

set -e

DB_NAME="practice_sessions"
DB_USER="postgres"
DB_HOST="localhost"
DB_PORT="5432"

echo "Creating database: $DB_NAME"

# Try to create the database
psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -tc "SELECT 1 FROM pg_database WHERE datname = '$DB_NAME'" | grep -q 1 || \
psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -c "CREATE DATABASE $DB_NAME"

echo "Database '$DB_NAME' is ready!"
echo "Connection string: postgresql://$DB_USER:password@$DB_HOST:$DB_PORT/$DB_NAME"
