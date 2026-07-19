import React from 'react'
import { mount, shallow } from 'enzyme'
import renderer from 'react-test-renderer'
import CohortDetails from './CohortDetails'
import CohortData from '../data/Cohort'

describe('Cohort Details Component', () => {
  // Test 1: should create the component
  test('should create the component', () => {
    const wrapper = shallow(<CohortDetails cohort={CohortData[0]} />)
    expect(wrapper.exists()).toBe(true)
  })

  // Test 2: should initialize the props
  test('should initialize the props', () => {
    const cohort = CohortData[0]
    const wrapper = mount(<CohortDetails cohort={cohort} />)
    expect(wrapper.props().cohort).toEqual(cohort)
  })

  // Test 3: should display cohort code in h3
  test('should display cohort code in h3', () => {
    const cohort = CohortData[1]
    const wrapper = mount(<CohortDetails cohort={cohort} />)
    expect(wrapper.find('h3').text()).toBe(cohort.code)
  })

  // Test 4: should always render same html (snapshot test)
  test('should always render same html', () => {
    const tree = renderer.create(<CohortDetails cohort={CohortData[0]} />).toJSON()
    expect(tree).toMatchSnapshot()
  })
})
