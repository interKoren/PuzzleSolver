package koren.proj.puzzlesolver

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/")
class PuzzleSolverController {

    @GetMapping("hello")
    fun helloWorld(): String = "Work does it?"
}