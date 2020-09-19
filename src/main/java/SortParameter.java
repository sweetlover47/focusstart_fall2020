public enum SortParameter {
    NO_TYPE,
    STRING_DECREASING,
    STRING_INCREASING,
    INT_DECREASING,
    INT_INCREASING;

    private String type;
    private String mode;

    public void setType(String newType) {
        type = newType;
    }

    public String getType() {
        return type;
    }

    public void setMode(String newMode) {
        mode = newMode;
    }

    public String getMode() {
        return mode;
    }

    public SortParameter getFinalSortParameter() {
        SortParameter sortParameter = NO_TYPE;
        if (type == null || mode == null)
            return NO_TYPE;
        if (type.equals("-i")) {
            if (mode.equals("-d"))
                sortParameter = INT_DECREASING;
            else
                sortParameter = INT_INCREASING;
        } else {
            if (mode.equals("-d"))
                sortParameter = STRING_DECREASING;
            else
                sortParameter = STRING_INCREASING;
        }
        sortParameter.setType(type);
        sortParameter.setMode(mode);
        return sortParameter;
    }
}
