SET GLOBAL event_scheduler = "ON";
DROP EVENT IF EXISTS javabase.CLEANUP;
CREATE EVENT CLEANUP ON SCHEDULE EVERY 1 MINUTE
STARTS CURRENT_TIMESTAMP ENDS CURRENT_TIMESTAMP + INTERVAL 10 YEAR ON COMPLETION PRESERVE ENABLE
DO START TRANSACTION;
    UPDATE flights AS F
    SET number_of_seats = number_of_seats + (
        SELECT COUNT(*) FROM reservations AS R
        WHERE R.flight_id = F.flight_id AND ( R.status = 'FAILED' OR R.timestamp < NOW()-300 )
    );

    UPDATE reservations
    SET status = 'CANCELLED'
    WHERE status = 'FAILED' OR timestamp<NOW()-300;
COMMIT;