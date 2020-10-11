CREATE TABLE credit_card
(
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    alias       VARCHAR(255) NULL,
    card_number VARCHAR(16)  NOT NULL,
    externalId  VARCHAR(36)  NULL,
    CONSTRAINT uq_card_number
        UNIQUE (card_number)
);

