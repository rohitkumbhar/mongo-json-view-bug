
### Bug Repro
In support of https://github.com/micronaut-projects/micronaut-data/issues/1729

Steps to repro:
1. Setup Mongodb database and update connection URL
2. Start application
3. Create a planet 
```
> curl http://localhost:8080/create/jupiter
{"id":{"timestamp":1662830706,"date":1662830706000},"name":"jupiter","internalName":"Internal jupiter"}
```
4. Verify db record `jupiter` gets created without a name:
```
{
  "_id": {
    "$oid": "631cc872174dd724a7fba522"
  },
  "internalName": "Internal jupiter"
}
```
5. Manually update db record to add a field `name` with value `jupiter`
6. Get a planet's full data
```
 curl http://localhost:8080/all/jupiter
{"id":{"timestamp":1662830706,"date":1662830706000},"name":null,"internalName":"Internal jupiter"}
```
7. Get planet's public view (implemented using JsonView)
```
curl http://localhost:8080/public/jupiter
{"name":null}
```