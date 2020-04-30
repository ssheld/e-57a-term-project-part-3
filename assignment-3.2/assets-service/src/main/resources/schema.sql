DROP TABLE IF EXISTS assets;

CREATE TABLE assets
(
    asset_id        VARCHAR(100) PRIMARY KEY NOT NULL,
    organization_id TEXT                     NOT NULL,
    asset_name      TEXT                     NOT NULL,
    asset_type      TEXT                     NOT NULL,
    comment         VARCHAR(100)
);


INSERT INTO assets (asset_id, organization_id, asset_name, asset_type, comment)
VALUES ('c4f7d738-66c2-11ea-bc55-0242ac130003', 'd3a393a8-66c2-11ea-bc55-0242ac130003', 'printers', 'operating',
        'valuable asset');
INSERT INTO assets (asset_id, organization_id, asset_name, asset_type, comment)
VALUES ('2cdcb4c2-66c3-11ea-bc55-0242ac130003', '32bc7e4a-66c3-11ea-bc55-0242ac130003', 'land', 'physical',
        'appreciating asset');
INSERT INTO assets (asset_id, organization_id, asset_name, asset_type, comment)
VALUES ('56cbec3a-66c3-11ea-bc55-0242ac130003', 'd3a393a8-66c2-11ea-bc55-0242ac130003', 'servers', 'operating',
        'valuable asset');
INSERT INTO assets (asset_id, organization_id, asset_name, asset_type, comment)
VALUES ('758cb9ce-66c3-11ea-bc55-0242ac130003', '7a6926e4-66c3-11ea-bc55-0242ac130003', 'tractor', 'operating',
        'depreciating asset');
