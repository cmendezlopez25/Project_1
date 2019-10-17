-- Reimbursement
-- DROP TABLE public."Reimbursement";

CREATE TABLE public."Reimbursement" (
	id serial NOT NULL,
	"type" text NOT NULL,
	amount numeric(10,2) NOT NULL,
	status text NOT NULL,
	datecreated timestamp NOT NULL,
	datelastmodified timestamp NOT NULL,
	username text NULL,
	CONSTRAINT reimbursement_pk PRIMARY KEY (id),
	CONSTRAINT reimbursement_fk FOREIGN KEY (username) REFERENCES "User"(username) ON DELETE SET NULL
);

-- Permissions

ALTER TABLE public."Reimbursement" OWNER TO postgres;
GRANT ALL ON TABLE public."Reimbursement" TO postgres;

-- User
CREATE TABLE public."User" (
	id serial NOT NULL,
	username text NOT NULL,
	"password" text NOT NULL,
	firstname text NOT NULL,
	lastname text NOT NULL,
	"role" text NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT user_un UNIQUE (username)
);

-- Permissions

ALTER TABLE public."User" OWNER TO postgres;
GRANT ALL ON TABLE public."User" TO postgres;

-- Notification
CREATE TABLE public."Notification" (
	id serial NOT NULL,
	msg text NULL,
	sender text NULL,
	datecreated timestamp NOT NULL,
	status text NOT NULL,
	receiver text NOT NULL,
	reimbid int4 NULL,
	CONSTRAINT notification_pk PRIMARY KEY (id),
	CONSTRAINT notification_fk FOREIGN KEY (receiver) REFERENCES "User"(username) ON DELETE CASCADE,
	CONSTRAINT notification_reimb_fk FOREIGN KEY (reimbid) REFERENCES "Reimbursement"(id) ON DELETE SET NULL,
	CONSTRAINT notification_sender_fk FOREIGN KEY (sender) REFERENCES "User"(username) ON DELETE CASCADE
);

-- Permissions
ALTER TABLE public."Notification" OWNER TO postgres;
GRANT ALL ON TABLE public."Notification" TO postgres;