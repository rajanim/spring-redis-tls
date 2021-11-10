import redis

try:
    r = redis.Redis(host='redis-11131.re-cluster1.ps-redislabs.org', port=11131, ssl=True, ssl_cert_reqs='required',ssl_ca_certs='tests/tls/proxy_cert.pem')
    print(r.info())
except Exception as e:
    print(e)