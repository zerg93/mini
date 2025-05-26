-- 일반 사용자 생성
CREATE USER appuser WITH PASSWORD 'app1234';

-- mini 스키마 생성 및 root에게 소유권 부여
CREATE SCHEMA IF NOT EXISTS mini AUTHORIZATION root;

-- root 접속 시 기본 search_path 설정
ALTER ROLE root SET search_path TO mini;
ALTER ROLE appuser SET search_path TO mini;

-- appuser에게 mini 스키마 접근 권한 부여
GRANT USAGE ON SCHEMA mini TO appuser;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA mini TO appuser;

-- 시퀀스 권한 (SERIAL, ID용)
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA mini TO appuser;

-- 앞으로 생성될 테이블에 대한 권한도 자동 부여
ALTER DEFAULT PRIVILEGES IN SCHEMA mini
  GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO appuser;

-- 기존 default privileges는 테이블만 설정했음 → 시퀀스도 포함해야 함
ALTER DEFAULT PRIVILEGES IN SCHEMA mini
    GRANT USAGE, SELECT ON SEQUENCES TO appuser;

-- mini 스키마에 테이블 생성
CREATE TABLE mini.service (
    service_id SERIAL PRIMARY KEY,
    service_code VARCHAR(10) NOT NULL UNIQUE,
    service_eng_name VARCHAR(50) NOT NULL UNIQUE,
    service_kor_name VARCHAR(50) NOT NULL UNIQUE,
    inst_code VARCHAR(10) NOT NULL,
    topic_code VARCHAR(10) NOT NULL,
    use_yn CHAR(1) NOT NULL DEFAULT 'Y' CHECK (use_yn IN ('Y', 'N')),
    created_at TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE mini.api (
    api_id SERIAL PRIMARY KEY,
    service_code VARCHAR(10) NOT NULL,
    api_eng_name VARCHAR(50) NOT NULL,
    api_kor_name VARCHAR(50) NOT NULL,
    request_url VARCHAR(200) NOT NULL,
    response_url VARCHAR(200),
    reverse_yn CHAR(1) NOT NULL CHECK (reverse_yn IN ('Y', 'N')),
    use_yn CHAR(1) NOT NULL DEFAULT 'Y' CHECK (use_yn IN ('Y', 'N')),
    created_at TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE mini.inst (
    inst_id SERIAL PRIMARY KEY,
    inst_name VARCHAR(50) NOT NULL UNIQUE,
    inst_code VARCHAR(10) NOT NULL UNIQUE,
    use_yn CHAR(1) NOT NULL DEFAULT 'Y' CHECK (use_yn IN ('Y', 'N')),
    created_at TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE mini.topic (
    topic_id SERIAL PRIMARY KEY,
    topic_code VARCHAR(10) NOT NULL UNIQUE,
    use_yn CHAR(1) NOT NULL DEFAULT 'Y' CHECK (use_yn IN ('Y', 'N')),
    created_at TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE mini.log (
    log_id SERIAL PRIMARY KEY,
    trace_id VARCHAR(44) NOT NULL,
    inst_code VARCHAR(10) NOT NULL,
    service_code VARCHAR(10) NOT NULL,
    api_eng_name VARCHAR(50) NOT NULL,
    result_code VARCHAR(5) NOT NULL,
    use_yn CHAR(1) NOT NULL DEFAULT 'Y' CHECK (use_yn IN ('Y', 'N')),
    created_at TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE mini.link_system (
    link_system_id SERIAL PRIMARY KEY,
    inst_code VARCHAR(10) NOT NULL,
    system_name VARCHAR(50) NOT NULL,
    ip_address VARCHAR(50) NOT NULL,
    use_yn CHAR(1) NOT NULL DEFAULT 'Y' CHECK (use_yn IN ('Y', 'N')),
    created_at TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW()
);

-- ✅ 테이블 생성 이후 실제 권한 부여 (중요!)
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA mini TO appuser;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA mini TO appuser;
-- appuser가 mini 스키마 안에서 테이블을 만들 수 있도록
GRANT CREATE ON SCHEMA mini TO appuser;

INSERT INTO mini.inst (inst_name, inst_code, use_yn)
VALUES
    ('서울시청', 'I0000001', 'Y'),
    ('부산시청', 'I0000002', 'Y'),
    ('대구시청', 'I0000003', 'Y'),
    ('김포시청','I0000004','Y'),
    ('네이버','I0000005','Y'),
    ('카카오','I0000006','Y');

INSERT INTO mini.topic (topic_code, use_yn)
VALUES ('T00001', 'Y'),
       ('T00002', 'Y'),
       ('T00003', 'Y'),
       ('T00004', 'Y'),
       ('T00005', 'Y'),
       ('T00006', 'Y');

INSERT INTO mini.link_system (inst_code, system_name, ip_address, use_yn)
VALUES
    ('I0000004', '김포시청 시스템', 'localhost:9999', 'Y'),
    ('I0000006', '카카오 시스템', 'localhost:8888', 'Y');

INSERT INTO mini.service (service_code, service_eng_name, service_kor_name, inst_code, topic_code, use_yn)
VALUES
    ('S00001', 'getTrain', '지하철 조회', 'I0000004', 'T00001', 'Y');

INSERT INTO mini.api (service_code, api_eng_name, api_kor_name, request_url, response_url, reverse_yn, use_yn)
VALUES
    ('S00001', 'getTrainInfo', '지하철 정보 조회', '/api/I0000004/getTrain/getTrainInfo', '/api/getTrainInfo', 'N', 'Y'),
    ('S00001', 'getTrainStatus', '지하철 상태 조회', '/api/I0000004/getTrain/getTrainStatus', '/api/getTrainStatus', 'N', 'Y');

