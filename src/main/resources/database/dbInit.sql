CREATE SEQUENCE names_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TYPE name_type AS ENUM
    ('last_name', 'first_name');
CREATE TABLE names
(
    id integer NOT NULL DEFAULT nextval('names_id_seq'::regclass),
    value text NOT NULL,
    name_type name_type,
    CONSTRAINT names_pkey PRIMARY KEY (id),
    CONSTRAINT values_type UNIQUE (value, name_type)
)


CREATE SEQUENCE rebounding_skills_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE rebounding_skills
(
    id integer NOT NULL DEFAULT nextval('rebounding_skills_id_seq'::regclass),
    position_technics integer not null,
    timing integer not null,
    CONSTRAINT rebounding_skills_pkey PRIMARY KEY (id)
)

CREATE SEQUENCE technical_skills_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE technical_skills
(
    id integer NOT NULL DEFAULT nextval('technical_skills_id_seq'::regclass),
    dribble integer not null,
    CONSTRAINT technical_skills_pkey PRIMARY KEY (id)
)

CREATE SEQUENCE physical_skills_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE physical_skills
(
    id integer NOT NULL DEFAULT nextval('physical_skills_id_seq'::regclass),
    strength integer not null,
    standing_vertical_jump integer not null,
    CONSTRAINT physical_skills_pkey PRIMARY KEY (id)
)

CREATE SEQUENCE shooting_skills_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE shooting_skills
(
    id integer NOT NULL DEFAULT nextval('shooting_skills_id_seq'::regclass),
    mi_distance integer not null,
    three_point integer not null,
    lay_up integer not null,
    CONSTRAINT shooting_skills_pkey PRIMARY KEY (id)
)

CREATE SEQUENCE shooting_skills_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE shooting_skills
(
    id integer NOT NULL DEFAULT nextval('shooting_skills_id_seq'::regclass),
    mi_distance integer not null,
    three_point integer not null,
    CONSTRAINT shooting_skills_pkey PRIMARY KEY (id)
)

CREATE SEQUENCE offensive_skills_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE offensive_skills
(
    id integer NOT NULL DEFAULT nextval('offensive_skills_id_seq'::regclass),
    shooting_skills_id integer NOT NULL,
    CONSTRAINT offensive_skills_pkey PRIMARY KEY (id),
    CONSTRAINT shooting_skills_id_fk FOREIGN KEY (shooting_skills_id)
    REFERENCES shooting_skills (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)

CREATE SEQUENCE player_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE player
(
    id integer NOT NULL DEFAULT nextval('player_id_seq'::regclass),
    first_name text NOT NULL,
    last_name text NOT NULL,
    age integer NOT NULL,
    height integer NOT NULL,
    offensive_skills_id integer NOT NULL,
    rebounding_skills_id integer NOT NULL,
    technical_skills_id integer NOT NULL,
    physical_skills_id integer NOT NULL
    CONSTRAINT player_pkey PRIMARY KEY (id)
    CONSTRAINT offensive_skills_id_fk FOREIGN KEY (offensive_skills_id)
    CONSTRAINT rebounding_skills_id_fk FOREIGN KEY (rebounding_skills_id)
    CONSTRAINT physical_skills_id_fk FOREIGN KEY (physical_skills_id)
    CONSTRAINT technical_skills_id_fk FOREIGN KEY (technical_skills_id)
    REFERENCES offensive_skills (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)

CREATE SEQUENCE team_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE team
(
    id integer NOT NULL DEFAULT nextval('team_id_seq'::regclass),
    name text NOT NULL,
    CONSTRAINT team_pkey PRIMARY KEY (id)
)


CREATE SEQUENCE team_player_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TYPE player_position_type AS ENUM
    ('POINT_GARD', 'SHOOTING_GARD', 'SMALL_FOWARD', 'POWER_FORWARD', 'CENTER', 'SUBSTITUTE');

CREATE TABLE team_player
(
    id integer NOT NULL DEFAULT nextval('team_player_id_seq'::regclass),
    player_id bigint NOT NULL,
    CONSTRAINT team_summoner_pkey PRIMARY KEY (id),
    CONSTRAINT player_id_fk FOREIGN KEY (player_id)
        REFERENCES player (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE SEQUENCE team_players_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE team_players
(
    id integer NOT NULL DEFAULT nextval('team_players_id_seq'::regclass),
    team_player_id bigint NOT NULL,
    team_id bigint NOT NULL,
    CONSTRAINT team_player_pkey PRIMARY KEY (id),
    CONSTRAINT team_player_id_fk FOREIGN KEY (team_player_id)
        REFERENCES team_player (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT team_id_fk FOREIGN KEY (team_id)
        REFERENCES team (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)