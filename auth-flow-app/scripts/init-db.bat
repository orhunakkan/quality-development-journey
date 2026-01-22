@echo off
REM Database initialization script for Windows
REM Creates the practice_sessions database for the auth-flow-app

setlocal enabledelayedexpansion

set DB_NAME=practice_sessions
set DB_USER=postgres
set DB_HOST=localhost
set DB_PORT=5432

echo Creating database: %DB_NAME%

REM Create the database using psql
psql -h %DB_HOST% -p %DB_PORT% -U %DB_USER% -c "CREATE DATABASE %DB_NAME%;" 2>nul

REM Check if database was created successfully
psql -h %DB_HOST% -p %DB_PORT% -U %DB_USER% -d %DB_NAME% -c "SELECT 1;" >nul 2>&1

if !errorlevel! equ 0 (
    echo Database '%DB_NAME%' is ready!
    echo Connection string: postgresql://%DB_USER%:password@%DB_HOST%:%DB_PORT%/%DB_NAME%
) else (
    echo Database creation may have failed or database already exists
    echo Connection string: postgresql://%DB_USER%:password@%DB_HOST%:%DB_PORT%/%DB_NAME%
)

endlocal
