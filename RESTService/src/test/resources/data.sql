drop table if exists git_commit;
drop table if exists commit_file;

CREATE TABLE git_commit(
  id INT AUTO_INCREMENT,
  `hash`  VARCHAR(64) NOT NULL,
  `timestamp`  DATE NOT NULL,

  PRIMARY KEY (`id`)
);

CREATE TABLE commit_file (
    id INT AUTO_INCREMENT,
    path VARCHAR(255) NOT NULL,
    lines_removed int,
    lines_added int,
    contributor_count int,
    minor_contributor_count int,
    lines_by_highest_contributor int,
    hunk int,
    commit_hash VARCHAR(64),

    PRIMARY KEY(`id`)
);


create index commit_file_index on commit_file (`commit_hash`);
create unique index git_commit_index on git_commit (`hash`);



insert into git_commit (`hash`, `timestamp`) values
    ('x', '2020-10-14'),
    ('b', '2020-10-14'),
    ('c', '2020-10-15'),
    ('d', '2020-10-17'),
    ('e', '2020-10-18'),
    ('f', '2020-10-19'),
    ('g', '2020-10-20');


insert into commit_file (path, lines_removed, lines_added, contributor_count, minor_contributor_count, lines_by_highest_contributor, hunk, commit_hash) values
    ('src/file_one.c', 10, 100, 1, 0, 90, 1,   'x'),
    ('src/file_two.c', 80, 20, 1, 0, 90, 1,    'x'),
    ('src/file_three.c', 10, 100, 1, 0, 90, 1, 'x'),
    ('src/file_one.c', 10, 100, 1, 0, 90, 1,   'b'),
    ('src/file_four.c', 10, 100, 1, 0, 90, 1,  'c'),
    ('src/file_four.c', 10, 100, 1, 0, 90, 1,  'd'),
    ('src/file_one.c', 10, 100, 1, 0, 90, 1,   'e'),
    ('src/file_three.c', 10, 100, 1, 0, 90, 1, 'f'),
    ('src/file_two.c', 10, 100, 1, 0, 90, 1,   'g');



