load('//tools/bzl:genrule2.bzl', 'genrule2')

CORE = [
  'commit-message-length-validator',
  'download-commands',
  'hooks',
  'replication',
  'reviewnotes',
  'singleusergroup'
]

genrule2(
  name = 'core',
  srcs = ['//plugins/%s:%s_deploy.jar' % (n, n) for n in CORE],
  cmd = 'mkdir -p $$TMP/WEB-INF/plugins;' +
    'for s in $(SRCS) ; do ' +
    'ln -s $$ROOT/$$s $$TMP/WEB-INF/plugins;done;' +
    'cd $$TMP;' +
    'zip -qr $$ROOT/$@ .',
  out = 'core.zip',
  visibility = ['//visibility:public'],
)
