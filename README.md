# pocketbookrepair
Spring boot project

### Rest API

**PartController:**

GET **/api/part** get all parts </br>
GET **/api/part/{id}** get part </br>
POST **/api/part** { ... } add new part </br>
PUT	**/api/part/{id}** { ... } change part </br>
DELETE	**/api/part/{id}** delete part </br>
(name(String), specification(String), fullSpecification(String), description(String), buy(int), sale(int), deviceId(int))

**DeviceController:**

GET **/api/device** get all devices </br>
GET **/api/device/{id}** get device </br>
POST **/api/device** { ... } add new device </br>
PUT	**/api/device/{id}** { ... } change device </br>
DELETE	**/api/device/{id}** delete device </br>
(maker(String), name(String), specification(String), fullSpecification(String), state(String), description(String), buy(int), sale(int), cpu(String), gpu(String), ram(int), hdd(int), battery(boolean), defect(String), made(date))

**DeviceTrackController:**

GET **/api/deviceTrack** get all deviceTrack </br>
GET **/api/deviceTrack/{id}** get deviceTrack </br>
POST **/api/deviceTrack** { ... } add new deviceTrack </br>
PUT	**/api/deviceTrack/{id}** { ... } change deviceTrack </br>
DELETE	**/api/deviceTrack/{id}** delete deviceTrack </br>
(maker(String), name(String), specification(String), fullSpecification(String), state(String), description(String), buy(int), sale(int), cpu(String), gpu(String), ram(int), hdd(int), battery(boolean), defect(String), made(date))