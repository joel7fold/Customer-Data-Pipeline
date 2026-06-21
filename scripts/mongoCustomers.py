import urllib.request

url = "http://localhost:8080/api/customers/mongo"
output = "mongo_customers.txt"

try:
    urllib.request.urlretrieve(url, output)
    print(f"MongoDB API response saved to {output}")
except Exception as e:
    print(f"Error consuming mongo API: {e}")