# This is a simple performance tests that simulates load on the microservice

echo "Starting calls..."
echo ""
 for i in {1..500}; do curl -X 'GET' 'http://traefik.valen.net/swapi-consumer/list' -H 'accept: */*' -H 'Authorization: Basic dmFsZW50aW46cm9qbw=='; done
echo ""
echo "Calls ended"