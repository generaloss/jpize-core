package jpize.context.input;

public enum CursorMode {

    NORMAL,   // Makes the cursor visible and behaving normally.
    HIDDEN,   // Makes the cursor invisible when it is over the content area of the window but does not restrict the cursor from leaving.
    DISABLED, // Hides and grabs the cursor, providing virtual and unlimited cursor movement. This is useful for implementing for example 3D camera controls.
    CAPTURED  // Makes the cursor visible and confines it to the content area of the window.

}
