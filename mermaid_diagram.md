```mermaid
graph TD
    subgraph "User Facing Features"
        A["Onboarding / Welcome"]
        B["Topic Selection"]
        C["News Feed (Content Consumption)"]
        D["Full Article Viewing"]
        E["Favorites / Saved Articles Screen"]
        F["User Profile Screen"]
    end

    subgraph "Supporting Services"
        BG_SYNC(((Background Data Sync)))
        LOCAL_DB(((Local Data Management)))
    end

    %% User Flow
    A --> B
    B --> C
    C --> D
    C ---> E
    C ---> F

    %% Interactions (Saving/Favoriting)
    D -- "Save Article" --> E
    C -- "Save Article (from feed)" --> E

    %% Dependencies on Supporting Services
    C -.-> BG_SYNC
    C -.-> LOCAL_DB
    B -.-> LOCAL_DB
    D -.-> LOCAL_DB %% For caching viewed articles
    E -.-> LOCAL_DB
    E -.-> BG_SYNC %% For syncing favorites
    F -.-> LOCAL_DB
```
