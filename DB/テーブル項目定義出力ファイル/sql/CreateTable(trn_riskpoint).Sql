----------------------------------------
-- ÿΩ∏Œﬂ≤›ƒ ÇÃCREATE TABLEï∂
-- çÏê¨ì˙éû ÅF 2021/08/07 13:48:09
----------------------------------------

psql -U postgres

-- DROP TABLE
DROP TABLE trn_riskpoint;

-- CREATE TABLE
CREATE TABLE trn_riskpoint(
  caseManagementId integer NOT NULL 
, title varchar(128) NOT NULL DEFAULT '' 
, bodyText varchar(128) NOT NULL DEFAULT '' 
, explainArgA integer 
, explainArgZ integer 
, riskpoint integer NOT NULL DEFAULT 0 
, CreateUser varchar(20)
, CreateDate timestamp
, UpdateUser varchar(20)
, UpdateDate timestamp
);

-- CREATE Primary Key
ALTER TABLE trn_riskpoint ADD CONSTRAINT trn_riskpoint_PK 
 PRIMARY KEY (caseManagementId);

