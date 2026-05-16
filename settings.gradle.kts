rootProject.name = "JavaLab"
include("integrationTests")

include("shared:domain")
include("shared:events")
include("shared:grpc")

include("orderService:presentation")
include("orderService:application")
include("orderService:infrastructure:persistence")
include("orderService:infrastructure:grpc")

include("storageService:domain")
include("storageService:presentation")
include("storageService:application")
include("storageService:infrastructure:persistence")
