CREATE USER scratchpad WITH password 'scratchpad';
CREATE DATABASE scratchpad WITH OWNER scratchpad;
\connect scratchpad
CREATE SCHEMA IF NOT EXISTS scratchpad;

GRANT ALL PRIVILEGES ON DATABASE scratchpad TO scratchpad;
GRANT ALL PRIVILEGES ON SCHEMA scratchpad TO scratchpad;
