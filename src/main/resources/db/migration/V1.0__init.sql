CREATE TABLE category
(
	id bigserial not null
		constraint category_pkey
			PRIMARY KEY,
	name varchar(255) not null
);

CREATE TABLE news
(
	id bigserial not null
		CONSTRAINT news_pkey
			PRIMARY KEY,
	content text not null,
	publication_date timestamp not null,
	title varchar(500)
);

CREATE TABLE news_categories
(
	news_id bigint not null
		CONSTRAINT newsfk
			REFERENCES news,
	category_id bigint not null
		CONSTRAINT categoryfk
			REFERENCES category
);

CREATE OR REPLACE FUNCTION make_tsvector(title TEXT, content TEXT)
  RETURNS tsvector AS $$
BEGIN
  RETURN (setweight(to_tsvector('russian', title),'A') ||
          setweight(to_tsvector('russian', content), 'B'));
END
$$ LANGUAGE 'plpgsql' IMMUTABLE;

CREATE index idx_fts_news
	ON news USING gin(make_tsvector(title::text, content));

