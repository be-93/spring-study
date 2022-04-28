INSERT INTO users(id, email, name, address)
SELECT
    gs as id,
    gs::text || 'email' as email,
    gs::text || '님' as name,
    gs::text || '님의 주소입니다.' as address
FROM     generate_series(2, 500000) as gs;