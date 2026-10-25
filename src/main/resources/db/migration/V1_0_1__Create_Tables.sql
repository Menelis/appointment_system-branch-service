CREATE TABLE provinces
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    version BIGINT NOT NULL DEFAULT 1,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    name VARCHAR(100) NOT NULL
);

CREATE TABLE cities
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    version BIGINT NOT NULL DEFAULT 1,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    name VARCHAR(100) NOT NULL,
    province_id UUID NOT NULL REFERENCES provinces(id)
);

CREATE TABLE branches
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    version BIGINT NOT NULL DEFAULT 1,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    name VARCHAR(50) NOT NULL,
    physical_address_line1 VARCHAR(255) NOT NULL,
    physical_address_line2 VARCHAR(255) NOT NULL,
    physical_suburb VARCHAR(50) NOT NULL,
    physical_province_id UUID NOT NULL REFERENCES provinces(id),
    physical_city_id UUID NOT NULL REFERENCES cities(id),
    physical_postal_code VARCHAR(10) NOT NULL,
    email VARCHAR(50) NOT NULL,
    fax_no VARCHAR(20) NOT NULL,
    land_line VARCHAR(20) NOT NULL
);