-- Insert data into CLIENT table
INSERT INTO CLIENT (CLIENT_ID, CLIENT_NAME, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_DATE)
VALUES
    (1, 'John Doe', '123-456-7890', 'john.doe@example.com', '2023-01-01'),
    (2, 'Jane Smith', '987-654-3210', 'jane.smith@example.com', '2023-02-15');

-- Insert data into CASES table
INSERT INTO CASES (CASE_ID, MEMBER_ID, STATUS, CASE_DATE_START, CASE_DATE_END, CASE_PROFIT, CASE_DESC, CASE_HOURS, CASE_TYPE)
VALUES
    (1, 1, 'Open', '2023-01-10', '2023-01-20', 500.00, 'Project A description', 30, 'Development'),
    (2, 2, 'Closed', '2023-02-01', '2023-02-28', 800.00, 'Project B description', 40, 'Marketing');

-- Insert data into PRODUCT table
INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_PRICE, PRODUCT_HISTORY_DESC, PRODUCT_MAIN_DESC, PRODUCT_YEAR, PRODUCT_NAME)
VALUES
    (1, 49.99, 'Product A history', 'Product A main description', 2022, 'Product A'),
    (2, 79.99, 'Product B history', 'Product B main description', 2021, 'Product B');

-- Insert data into CASE_JOURNAL table
INSERT INTO CASE_JOURNAL (JOURNAL_ID, CASE_ID, JOURNAL_DESC)
VALUES
    (1, 1, 'Journal entry for Project A'),
    (2, 2, 'Journal entry for Project B');

-- Insert data into PROD_IMG table
INSERT INTO PROD_IMG (PROD_IMG_ID, PRODUCT_ID, IMG_PATH_STRING)
VALUES
    (1, 1, '/images/product_a.jpg'),
    (2, 2, '/images/product_b.jpg');

-- Insert data into JOURNAL_IMG table
INSERT INTO JOURNAL_IMG (IMG_ID, JOURNAL_ID, IMG_PATH_STRING)
VALUES
    (1, 1, '/images/journal_entry_a.jpg'),
    (2, 2, '/images/journal_entry_b.jpg');
