.PHONY: compose-ps compose-up compose-down psql

compose-ps:
	docker-compose -f docker/docker-compose.yml ps

compose-up:
	docker-compose -f docker/docker-compose.yml up -d

compose-down:
	docker-compose -f docker/docker-compose.yml down

psql:
	docker exec -t postgres psql -U ${DB_USERNAME} -d cars -h localhost -p 5432

run:
	./gradlew bootRun