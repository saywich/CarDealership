rootProject.name = "JavaLab"
include("integrationTests")

include("shared:domain")
include("shared:events")

include("orderService:presentation")
include("orderService:application")
include("orderService:infrastructure:persistence")

include("storageService:domain")
include("storageService:presentation")
include("storageService:application")
include("storageService:infrastructure:persistence")
