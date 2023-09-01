package to.msn.wings.foxbrakeout

class CollisionDetection {
    fun isCollision(ball: Ball, obj: RectangleObject): Boolean {
        val ballBottom = ball.getBottom()
        val rectTop = obj.getTop()

        val rectRangeX = obj.getLeft()..obj.getRight()

        if (!(ball.getX() in rectRangeX)) return false
        if (ballBottom < rectTop) return false

        return true
    }
}