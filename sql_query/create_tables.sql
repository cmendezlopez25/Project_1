
-- Users 
CREATE TABLE "Users" (
	id serial NOT NULL,
	username text NOT NULL,
	"password" text NOT NULL,
	firstname text NOT NULL,
	lastname text NOT NULL,
	"role" text NOT NULL,
	"limit" numeric NOT NULL DEFAULT 1000,
	supervisor text NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT user_un UNIQUE (username),
	CONSTRAINT users_supervisor_fk FOREIGN KEY (supervisor) REFERENCES "Users"(username) ON DELETE SET NULL
);

-- Permissions

ALTER TABLE "Users" OWNER TO postgres;
GRANT ALL ON TABLE "Users" TO postgres;



-- Reimbursement
CREATE TABLE "Reimbursement" (
	id serial NOT NULL,
	"type" text NOT NULL,
	amount numeric(10,2) NOT NULL,
	status text NOT NULL,
	datecreated timestamp NOT NULL,
	datelastmodified timestamp NOT NULL,
	username text NULL,
	CONSTRAINT reimbursement_pk PRIMARY KEY (id),
	CONSTRAINT reimbursement_fk FOREIGN KEY (username) REFERENCES "Users"(username) ON DELETE SET NULL
);

-- Permissions

ALTER TABLE "Reimbursement" OWNER TO postgres;
GRANT ALL ON TABLE "Reimbursement" TO postgres;


-- Notification
CREATE TABLE "Notification" (
	id serial NOT NULL,
	msg text NULL,
	sender text NULL,
	datecreated timestamp NOT NULL,
	status text NOT NULL,
	receiver text NOT NULL,
	reimbid int4 NULL,
	CONSTRAINT notification_pk PRIMARY KEY (id),
	CONSTRAINT notification_fk FOREIGN KEY (receiver) REFERENCES "Users"(username) ON DELETE CASCADE,
	CONSTRAINT notification_reimb_fk FOREIGN KEY (reimbid) REFERENCES "Reimbursement"(id) ON DELETE SET NULL,
	CONSTRAINT notification_sender_fk FOREIGN KEY (sender) REFERENCES "Users"(username) ON DELETE CASCADE
);

-- Permissions

ALTER TABLE "Notification" OWNER TO postgres;
GRANT ALL ON TABLE "Notification" TO postgres;
