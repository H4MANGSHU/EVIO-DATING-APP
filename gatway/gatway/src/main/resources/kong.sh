rate_limit() {
  status=$(curl -s -o /dev/null -w "%{http_code}" \
    -X POST http://localhost:8001/plugins \
    --data "name=rate-limiting" \
    --data "config.minute=100" \
    --data "config.policy=redis" \
    --data "config.redis.host=redis" \
    --data "config.redis.port=6379")

  [ "$status" -eq 201 ]
}

if ! rate_limit; then
  echo "Error: Failed to configure rate limiting."
  exit 1
fi

echo "Rate limiting configured successfully."
