CREATE DATABASE IF NOT EXISTS testdb;

CREATE TABLE IF NOT EXISTS "document" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"name" text NOT NULL,
	"content" text NOT NULL,
	"creation_date" timestamp without time zone NOT NULL,
	"sign_date" timestamp without time zone,
	"access_user" text NOT NULL,
	PRIMARY KEY ("id")
);