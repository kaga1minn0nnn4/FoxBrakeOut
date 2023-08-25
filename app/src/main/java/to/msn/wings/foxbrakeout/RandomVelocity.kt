package to.msn.wings.foxbrakeout

import java.util.Random

class RandomVelocity(private val averageVerocity: Float = 10f) {

    private fun getRandomFactor(): Float {
        val randomNum = Math.random()
        return (randomNum.toFloat()) + 0.5f
    }

    fun getVerocity(): Float {
        return averageVerocity * getRandomFactor()
    }

    companion object {
        fun init(): RandomVelocity {
            return RandomVelocity()
        }

        fun initFromAverageVelocity(average: Float): RandomVelocity {
            return RandomVelocity(average)
        }
    }
}