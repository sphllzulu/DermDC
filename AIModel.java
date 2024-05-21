import java.util.List;

/**
 * Represents an AI model in the system.
 * Each AI model has a unique ID, name, description, and the user who created it.
 */
public class AIModel {
    private int modelId;
    private String name;
    private String description;
    private User createdBy;
    private List<byte[]> largeDataObjects; // Example of large object support

    // Constructors, getters, and setters

    public AIModel(int modelId, String name, String description, User createdBy) {
        this.modelId = modelId;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<byte[]> getLargeDataObjects() {
        return largeDataObjects;
    }

    public void setLargeDataObjects(List<byte[]> largeDataObjects) {
        this.largeDataObjects = largeDataObjects;
    }
}
