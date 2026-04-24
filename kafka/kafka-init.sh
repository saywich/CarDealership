#!/bin/sh

BOOTSTRAP_SERVER="kafka:29092"

until /opt/kafka/bin/kafka-topics.sh \
  --bootstrap-server "$BOOTSTRAP_SERVER" \
  --list; do
  sleep 2
done

echo "Creating topics";

create_topic() {
  /opt/kafka/bin/kafka-topics.sh \
    --bootstrap-server "$BOOTSTRAP_SERVER" \
    --create \
    --if-not-exists \
    --topic "$1" \
    --partitions 1 \
    --replication-factor 1
}

create_topic in-stock-order-request
create_topic in-stock-order-response

create_topic manufacture-order-request
create_topic manufacture-order-response

create_topic test-drive-order-request
create_topic test-drive-order-response