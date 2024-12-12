
CREATE TABLE "categories" (
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT category_name_unique UNIQUE (name)
);

CREATE TABLE "components" (
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL,
    category_id INTEGER NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    CONSTRAINT fk_category_component FOREIGN KEY(category_id) REFERENCES categories(id),
    CONSTRAINT component_name_unique UNIQUE (name)
);

CREATE TABLE "users" (
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) NOT NULL,
    CONSTRAINT user_username_unique UNIQUE (username)
);

CREATE TABLE "builds" (
     id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     name VARCHAR(100) NOT NULL,
     user_id INTEGER NOT NULL,
     CONSTRAINT fk_user_build FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE builds_components (
     id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     build_id INTEGER NOT NULL,
     component_id INTEGER NOT NULL,
     CONSTRAINT fk_build FOREIGN KEY (build_id) REFERENCES builds(id),
     CONSTRAINT fk_component FOREIGN KEY (component_id) REFERENCES components(id)
);
