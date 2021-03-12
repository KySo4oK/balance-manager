CREATE TABLE "customer"
(
    "id"      serial  NOT NULL,
    "balance" integer NOT NULL,
    CONSTRAINT "customer_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );



CREATE TABLE "bank_account"
(
    "id"      serial  NOT NULL,
    "balance" integer NOT NULL,
    CONSTRAINT "bank_account_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );



CREATE TABLE "transaction"
(
    "id"          serial    NOT NULL,
    "time"        TIMESTAMP NOT NULL DEFAULT 'now()',
    "customer_id" integer   NOT NULL,
    "bank_id"     integer   NOT NULL,
    "amount"      integer   NOT NULL,
    CONSTRAINT "transaction_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );



ALTER TABLE "transaction"
    ADD CONSTRAINT "transaction_fk0" FOREIGN KEY ("customer_id") REFERENCES "customer" ("id");
ALTER TABLE "transaction"
    ADD CONSTRAINT "transaction_fk1" FOREIGN KEY ("bank_id") REFERENCES "bank_account" ("id");
