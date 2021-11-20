CREATE EVENT 'Cleanup' ON SCHEDULE EVERY 5 MINUTE ENABLE
DO BEGIN

	START TRANSACTION;

		UPDATE flights
		SET numberOfSeats = numberOfSeats + (
			SELECT COUNT(*) FROM reservations AS R
			WHERE R.flightId = flightId AND R.status = 'FAILED' OR R.timestamp<NOW()-600;
		);

		UPDATE reservations
		SET status = 'CANCELLED'
		WHERE status = 'FAILED' OR timestamp<NOW()-600;

	COMMIT;

END
