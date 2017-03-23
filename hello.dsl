freeStyleJob("Hello World")
{
  description ("Simple Hello World")
  triggers
  {
    scm('*/1 * * * *')
  }
    steps
  {
    shell ("echo helloworld")
  }
}