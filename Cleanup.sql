USE javabase;
SET GLOBAL event_scheduler = "ON";
DROP EVENT IF EXISTS javabase.CLEANUP;
delimiter $$
CREATE EVENT javabase.CLEANUP ON SCHEDULE EVERY 1 MINUTE
STARTS CURRENT_TIMESTAMP ON COMPLETION PRESERVE ENABLE
DO 
begin
START TRANSACTION;
    UPDATE javabase.flights AS F
    SET number_of_seats = number_of_seats + (
        SELECT COUNT(*) FROM javabase.reservations AS R
        WHERE R.flight_id = F.flight_id AND ( R.status = 'FAILED' OR R.timestamp < NOW()-60 )
    );

    UPDATE javabase.reservations
    SET status = 'CANCELLED'
    WHERE status = 'FAILED' OR timestamp<NOW()-60;
COMMIT;
end $$
delimiter ;