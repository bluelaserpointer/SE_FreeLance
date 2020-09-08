const mockData = false

const validateStub = {
  render: () => {},
  methods: {
    validate: () => {}
  }
}

jest.unmock('axios')
import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

import { createLocalVue, mount, shallowMount } from '@vue/test-utils'
import MailEntityPanel from '@/components/article/MailEntityPanel'
import Element from 'element-ui'

const localVue = createLocalVue()
localVue.use(Element)

describe('MailEntityPanel.vue', () => {
  const wrapper = shallowMount(MailEntityPanel,
    {
      localVue,
      stubs: {
        'el-form': validateStub
      }
    })

  const mockAdapter = new MockAdapter(axios)
  const spyPost = jest.spyOn(axios, 'post')

  mockAdapter.onPost('mail/List').reply(200, mockData)

  it('Startup', async() => {
    await wrapper.vm.getList()
  })

  it('Mail Entity Panel Nulls created getList watchList', async() => {
    expect(wrapper.vm.panelVisible).toBeFalsy()
    expect(wrapper.vm.list).toStrictEqual(null)
    expect(spyPost).toHaveBeenCalledTimes(1)
  })
})
