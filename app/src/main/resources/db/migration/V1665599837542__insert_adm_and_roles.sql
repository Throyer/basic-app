-- variables
SET @name = 'admin';
SET @email = 'admin@email.com';
SET @password = '$2a$10$QBuMJLbVmHzgvTwpxDynSetACNdCBjU5zgo.81RWEDzH46aUrgcNK';
SET @adm = 'ADM';
SET @user = 'USER';

-- insert admin
INSERT INTO user
  (name, email, password)
VALUES
  (@name, @email, @password);

-- insert roles
INSERT INTO role
  (name)
VALUES
  (@adm),
  (@user);

-- put roles into admin
INSERT INTO user_role
  (user_id, role_id)
VALUES
  (
    (SELECT id FROM user WHERE email = @email),
    (SELECT id FROM role WHERE name = @adm)
  ),
  (
    (SELECT id FROM user WHERE email = @email),
    (SELECT id FROM role WHERE name = @user)
  );